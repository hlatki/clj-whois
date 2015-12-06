;; A first attempt at parsing responses
(ns clj-whois.parser)

;;(defn find-attribute [attribute text]
;;  (let [attribute-pattern "\\s*%s\\s*:\\s+(\\S+)"
;;        new-regex (re-pattern (format attribute-pattern attribute))]
;;    (re-seq new-regex text)))

(defn find-value [attribute text]
  "Try to find an attribute in a response. Returns the raw list of matches for processing"
  (let [attribute-pattern "\\s*%s\\s*:\\s+([\\S \\t]+)"
        new-regex (re-pattern (format attribute-pattern attribute))]
    (re-seq new-regex text)))


;; TODO: Maybe combine match in string for address/owners and leave as list for name servers etc?
(defn process-matches [matches]
  "Process list of matches from find-value.  If there is more than one match, they are combined
  (one match per line of resulting string"
  (if (seq matches)
    (if (= (count matches) 1)
      (second (first matches))
      (map second matches))
      ;;(apply str (map #(str (second %) "\n") matches))) -- should probably leave as list?
    matches))

(defn get-attribute [key attribute-name text]
  "Extract an attribute from a whois response.
  Example:
    (get-attribute :owner \"Owner\" whois-response)
    => {:owner \"euphoria GmbH\\neuphoria GmbH\\nFernkorngasse 10\\nVienna\\nAT\\n\"\"}"
  {key (process-matches (find-value attribute-name text))})


;; Major TODO: how to deal with responses like:
;;Registrant's address:
;;149 New Montgomery Street
;;Third Floor
;;San Francisco
;;CA
;;94105
;;United States
