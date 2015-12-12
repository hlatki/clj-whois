;; A first attempt at parsing responses
(ns clj-whois.parser)


(defn- excep-helper
  "Actually do the work for val-not-exception macro"
  ([method object] (excep-helper method object nil))
  ([method object val]
   `(try (~method  ~object)
       (catch Exception ~'e ~val))))

(defmacro val-not-exception
  "Try calling method on object and return the result.  If an exception is thrown
  while evaluating method, return val instead. Returns nil if val isn't returned.
  args: method object value
  (value is optional)"
  [& args]
  (apply excep-helper args))


;;(defmacro val-not-exception
;;
;;  ([method object] (val-not-exception &form &env method object 1))
;;  ([method object v]
;;  `(try (~method  ~object)
;;       (catch Exception ~'e ~v))))


;; new approach "mapify" the response and then standardize it
;; regex for "key" in response
(def whois-key-regex #"[\n\r\t]+[\w '-]+:")

;; TODO: cleanup
;; TODO: what to do about problem parsing the end
;; TODO: strip newlines out of key
(defn mapify-response
  "Convert a WHOIS response to a map.
  This is based on the assumption that \"keys\" are defined by
  [\\n\\r\\t]+[\\w '-]+: and everything until the next key is
  its value. This is an unproven assertion."
  [response]
  (let [matcher (re-matcher whois-key-regex response)]
      (loop [prev-key nil
             prev-key-end nil
             key (re-find matcher)
             resp-map {}]
        (if (not key)
          resp-map
          (if (not prev-key)
            (recur key (val-not-exception .end matcher (count response)) (re-find matcher) resp-map)
            (recur key (val-not-exception .end matcher (count response)) (re-find matcher)
                   (assoc resp-map prev-key (subs response prev-key-end (dec (val-not-exception .start matcher (count response)))))))))))









;; First attempt query response for some attribute (e.g. "Domain")

(defn find-value [attribute text]
  "Try to find an attribute in a response. Returns the raw list of matches for processing"
  (let [attribute-pattern "\\s*%s\\s*:\\s+([\\S \\t]+)"
        new-regex (re-pattern (format attribute-pattern attribute))]
    (re-seq new-regex text)))


;; TODO: Maybe combine match in string for address/owners and leave as list for name servers etc?
(defn process-matches
  "Process list of matches from find-value.  If there is more than one match, they are combined
  (one match per line of resulting string"
  [matches]
  (if (seq matches)
    (if (= (count matches) 1)
      (second (first matches))
      (map second matches))
      ;;(apply str (map #(str (second %) "\n") matches))) -- should probably leave as list?
    matches))

(defn get-attribute
  "Extract an attribute from a whois response.
  Example:
    (get-attribute :owner \"Owner\" whois-response)
    => {:owner \"euphoria GmbH\\neuphoria GmbH\\nFernkorngasse 10\\nVienna\\nAT\\n\"\"}"
  [key attribute-name text]
  {key (process-matches (find-value attribute-name text))})


;; Major TODO: how to deal with responses like:
;;Registrant's address:
;;149 New Montgomery Street
;;Third Floor
;;San Francisco
;;CA
;;94105
;;United States
