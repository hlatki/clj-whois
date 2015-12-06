# clj-whois

A Clojure library for WHOIS. The code is very much in progress, so use at
your own risk.

## Usage

    (ns clj-whois.core-test
      (:require [clj-whois.core :as clj-whois]))

    (clj-whois/whois "google.it")

## TODO
- [ ] Figure out how to handle the wildly different formats returned by WHOIS servers
- [ ] Parse WHOIS responses instead of just returning strings

## License

Copyright © 2015 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
