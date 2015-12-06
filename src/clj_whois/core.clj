(ns clj-whois.core
  (:import [org.apache.commons.net.whois WhoisClient])
  (:require [clj-whois.whois-servers :as whois-servers]))

(def iana-whois-server "whois.iana.org")

(defn query
  "Wraps WhoisClient.query.
  whois-endpoint is the whois server you want to query
  url is the domain name you want to look up

  Examples:
    (whois \"whois.nic.it\" \"google.it\")
    (whois \"whois.iana.org\" \"com.\") -- this gets the whois server for the given tld
  "
  ([url] (query WhoisClient/DEFAULT_HOST url))
  ([whois-endpoint url]
  (let [wis (WhoisClient.)]
    (. wis connect whois-endpoint)
    (let [ret (try (. wis query url)
                   (catch java.io.IOException ex
                     (println ex)
                     "Failed"))]
      (. wis disconnect)
      ret))))

(defn parse-iana-response
  "Parse the response from iana-whois-server"
  [response]
  (let [matches (re-find #"whois:\s+(\S+)" response)]
    (if matches
      (peek matches)
      false)))

(defn get-whois-server-for-tld
  "Get the whois server for a given TLD."
  [tld]
  (if-let [whois-server (whois-servers/tld-to-whois-server-map tld)]
      whois-server
      (parse-iana-response (query iana-whois-server tld))))

(defn get-tld-from-url
  "Extract TLD from a URL"
  [url]
  (re-find #"\.\w+$" url))

(defn whois
  "Barebones whois lookup
  TODO: implement = stuff, actually parse response etc."
  [url]
  (let [tld (get-tld-from-url url)
        whois-server (get-whois-server-for-tld tld)]
    (if whois-server
      (query whois-server url)
      (str "Error: could not find whois server for TLD " tld))))

