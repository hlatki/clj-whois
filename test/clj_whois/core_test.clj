(ns clj-whois.core-test
  (:require [clojure.test :refer :all]
            [clj-whois.core :refer :all]
            [clj-whois.sample-whois-responses :refer :all]))


(deftest get-whois-from-valid-IANA-response
  (testing "Extract the whois server from a valid IANA reponse"
    (is (= "whois.nic.it" (parse-iana-response iana-tld-response-for-it)))))

(deftest get-whois-from-invalid-IANA-response
  (testing "Expect false instead of whois server from invalid IANA respinse"
    (is (= false (parse-iana-response "nope")))))
