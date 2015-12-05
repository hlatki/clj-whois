(ns clj-whois.core
  (:import [org.apache.commons.net.whois WhoisClient]))


(defn query
  "Wraps WhoisClient.query.
  whois-endpoint is the whois server you want to query
  url is the domain name you want to look up

  Examples:
    (whois \"whois.nic.it\" \"google.it\")
    (whois \"whois.iana.org\" \"com.\") -- this gets the whois server for the given tld
  "
  ([url] (whois WhoisClient/DEFAULT_HOST url))
  ([whois-endpoint url]
  (let [wis (WhoisClient.)]
    (. wis connect whois-endpoint)
    (let [ret (try (. wis query url)
                   (catch java.io.IOException ex
                     (println ex)
                     "Failed"))]
      (. wis disconnect)
      ret))))
