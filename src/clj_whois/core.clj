(ns clj-whois.core
  (:import [org.apache.commons.net.whois WhoisClient]))

(defn whois [url]
  (let [wis (WhoisClient.)]
    (. wis connect WhoisClient/DEFAULT_HOST)
    (let [ret (try (. wis query url)
                   (catch java.io.IOException ex
                     (println ex)
                     "Failed"))]
      (. wis disconnect)
      ret)))

