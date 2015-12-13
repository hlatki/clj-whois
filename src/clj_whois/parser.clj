;; A first attempt at parsing responses
(ns clj-whois.parser
  (:require [clojure.string :as string]))


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
;;TODO: Handle case where there isn't any whitespace before the key (i.e. key at beginning of string)
(def whois-key-regex #"[\n\r\t]+[\w '-]+:")

(defn- get-value-from-response
  "get that value -- helper function for mapify response"
  [response prev-key-end matcher response-count]
  (let [value-end (val-not-exception .start matcher (inc response-count))]
    (if (<= value-end prev-key-end)
      ""
      (subs response prev-key-end value-end))))




;; TODO: cleanup code
;; TODO: trim key and value
;; TODO: don't include : in key
;; TODO: "nested" keys -- i.e. key where the values are actually keys -- right now they get flattened
;; TODO: what to do about IT's missing : after Nameserver's -- need custom parser
;;
;;Nameservers
;;ns0.yepa.com
;;ns1.yepa.com


;; This produces a lot of unneeded keys, but it looks like it's mostly getting the legitimate one...
(defn mapify-response
  "Convert a WHOIS response to a map.
  This is based on the assumption that \"keys\" are defined by
  whois-key-regex and everything until the next key is
  its value. This is an unproven assertion."
  [response]
  (let [matcher (re-matcher whois-key-regex response)
        response-count (count response)]
      (loop [prev-key nil
             prev-key-end nil ;; TODO: put all prev stuff in a map to make it easier to check
             resp-map {}
             curr-key (re-find matcher)]
        (if (not curr-key)
          (if prev-key ;;reached the end of the string, need to associate the value with the prev key
            (assoc resp-map (string/trim prev-key) (string/trim (subs response (inc prev-key-end))))
            resp-map)
          (let [key-end (val-not-exception .end matcher response-count)]
            (if (not prev-key)
              (recur curr-key key-end resp-map (re-find matcher))
              (recur curr-key
                     key-end
                     (assoc resp-map (string/trim prev-key) (string/trim (get-value-from-response response prev-key-end matcher response-count)))
                     (re-find matcher))))))))











;; First attempt query response for some attribute (e.g. "Domain")

(defn find-value [attribute text]
  "Try to find an attribute in a response. Returns the raw list of matches for processing"
  (let [attribute-pattern "\\s*%s\\s*:\\s+([\\S \\t]+)"
        new-regex (re-pattern (format attribute-pattern attribute))]
    (re-seq new-regex text)))


;; TODO: Maybe combine match in string for address/owners and leave as list for name servers etc? -- do as additional parsing
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




(def uk-response "\r
     Domain name:\r
         wikipedia.co.uk\r
 \r
     Registrant:\r
         Wikimedia Foundation, Inc.\r
 \r
     Registrant type:\r
         Non-UK Corporation\r
 \r
     Registrant's address:\r
         149 New Montgomery Street\r
         Third Floor\r
         San Francisco\r
         CA\r
         94105\r
         United States\r
 \r
     Data validation:\r
         Nominet was not able to match the registrant's name and/or address against a 3rd party source on 10-Jun-2014\r
 \r
     Registrar:\r
         Markmonitor Inc. t/a MarkMonitor Inc. [Tag = MARKMONITOR]\r
         URL: http://www.markmonitor.com\r
 \r
     Relevant dates:\r
         Registered on: 25-Nov-2003\r
         Expiry date:  25-Nov-2016\r
         Last updated:  11-Sep-2015\r
 \r
     Registration status:\r
         Registered until expiry date.\r
 \r
     Name servers:\r
         dns11.register.com\r
         dns12.register.com\r
 \r
     WHOIS lookup made at 19:20:27 06-Dec-2015\r
 \r
 -- \r
 This WHOIS information is provided for free by Nominet UK the central registry\r
 for .uk domain names. This information and the .uk WHOIS are:\r
 \r
     Copyright Nominet UK 1996 - 2015.\r
 \r
 You may not access the .uk WHOIS or use any data from it except as permitted\r
 by the terms of use available in full at http://www.nominet.uk/whoisterms,\r
 which includes restrictions on: (A) use of the data for advertising, or its\r
 repackaging, recompilation, redistribution or reuse (B) obscuring, removing\r
 or hiding any or all of this notice and (C) exceeding query rate or volume\r
 limits. The data is provided on an 'as-is' basis and may lag behind the\r
 register. Access may be withdrawn or restricted at any time. \r
 ")

;;(def mapified-response (mapify-response uk-response))


;; Major TODO: how to deal with responses like:
;;Registrant's address:
;;149 New Montgomery Street
;;Third Floor
;;San Francisco
;;CA
;;94105
;;United States
