(ns clj-whois.sample-whois-responses)

(def iana-tld-response-for-it "% IANA WHOIS server
 % for more information on IANA, visit http://www.iana.org
 % This query returned 1 object

 domain:       IT

 organisation: IIT - CNR
 address:      Via Moruzzi, 1
 address:      Pisa  I-56124
 address:      Italy

 contact:      administrative
 name:         Domenico Laforenza
 organisation: IIT - CNR
 address:      Via Moruzzi, 1
 address:      Pisa  I-56124
 address:      Italy
 phone:        +39 050 315 2112
 fax-no:       +39 050 315 2113
 e-mail:       direttore@iit.cnr.it

 contact:      technical
 name:         Maurizio Martinelli
 organisation: IIT - CNR
 address:      Via Moruzzi, 1
 address:      Pisa  I-56124
 address:      Italy
 phone:        +39 050 315 2087
 fax-no:       +39 050 315 2207
 e-mail:       maurizio.martinelli@iit.cnr.it

 nserver:      A.DNS.IT 194.0.16.215 2001:678:12:0:194:0:16:215
 nserver:      DNS.NIC.IT 192.12.192.5 2a00:d40:1:1:0:0:0:5
 nserver:      M.DNS.IT 2001:1ac0:0:200:0:a5d1:6004:2 217.29.76.4
 nserver:      NAMESERVER.CNR.IT 194.119.192.34 2a00:1620:c0:220:194:119:192:34
 nserver:      R.DNS.IT 193.206.141.46 2001:760:ffff:ffff:0:0:0:ca
 nserver:      S.DNS.IT 194.146.106.30 2001:67c:1010:7:0:0:0:53

 whois:        whois.nic.it

 status:       ACTIVE
 remarks:      Registration information: http://www.nic.it/

 created:      1987-12-23
 changed:      2015-06-05
 source:       IANA

 ")

(def valid-and-registered-it-response
  "
 *********************************************************************
 * Please note that the following result could be a subgroup of      *
 * the data contained in the database.                               *
 *                                                                   *
 * Additional information can be visualized at:                      *
 * http://www.nic.it/cgi-bin/Whois/whois.cgi                         *
 *********************************************************************

 Domain:             wikipedia.it
 Status:             ok
 Created:            2003-03-04 00:00:00
 Last Update:        2015-07-10 01:05:37
 Expire Date:        2016-06-24

 Registrant
   Organization:     Associazione Wikipedia Italia
   Address:          Via Flaming, 49
                     Roma
                     00191
                     RM
                     IT
   Created:          2007-03-01 10:41:48
   Last Update:      2010-08-20 12:50:36

 Admin Contact
   Name:             Luca Venturini
   Organization:     Yepa S.r.l.

 Technical Contacts
   Name:             Luca Venturini
   Organization:     Yepa S.r.l.

 Registrar
   Organization:     Yepa s.r.l.
   Name:             YEPA-REG

 Nameservers
   ns0.yepa.com
   ns1.yepa.com

 ")

(def valid-and-available-it-response
  "Domain:             ndndndndn.it\nStatus:             AVAILABLE\n")

(def valid-and-registered-uk-response
  "\r
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

(def unregistered-response-from-uk
  "\r
     No match for \"wikipediadmdmdqwnqwbjbwhdda.co.uk\".\r
 \r
     This domain name has not been registered.\r
 \r
     WHOIS lookup made at 19:22:22 06-Dec-2015\r
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

(def registered-response-com
  "
 Whois Server Version 2.0

 Domain names in the .com and .net domains can now be registered
 with many different competing registrars. Go to http://www.internic.net
 for detailed information.

    Domain Name: CLOJURE.COM
    Registrar: REGISTER.COM, INC.
    Sponsoring Registrar IANA ID: 9
    Whois Server: whois.register.com
    Referral URL: http://www.register.com
    Name Server: NS1170.DNS.DYN.COM
    Name Server: NS2138.DNS.DYN.COM
    Name Server: NS3137.DNS.DYN.COM
    Name Server: NS4177.DNS.DYN.COM
    Status: clientTransferProhibited http://www.icann.org/epp#clientTransferProhibited
    Updated Date: 04-apr-2015
    Creation Date: 03-apr-2005
    Expiration Date: 03-apr-2016

 >>> Last update of whois database: Sun, 06 Dec 2015 19:26:34 GMT <<<

 For more information on Whois status codes, please visit
 https://www.icann.org/resources/pages/epp-status-codes-2014-06-16-en.

 NOTICE: The expiration date displayed in this record is the date the
 registrar's sponsorship of the domain name registration in the registry is
 currently set to expire. This date does not necessarily reflect the expiration
 date of the domain name registrant's agreement with the sponsoring
 registrar.  Users may consult the sponsoring registrar's Whois database to
 view the registrar's reported date of expiration for this registration.

 TERMS OF USE: You are not authorized to access or query our Whois
 database through the use of electronic processes that are high-volume and
 automated except as reasonably necessary to register domain names or
 modify existing registrations; the Data in VeriSign Global Registry
 Services' (\"VeriSign\") Whois database is provided by VeriSign for
 information purposes only, and to assist persons in obtaining information
 about or related to a domain name registration record. VeriSign does not
 guarantee its accuracy. By submitting a Whois query, you agree to abide
 by the following terms of use: You agree that you may use this Data only
 for lawful purposes and that under no circumstances will you use this Data
 to: (1) allow, enable, or otherwise support the transmission of mass
 unsolicited, commercial advertising or solicitations via e-mail, telephone,
 or facsimile; or (2) enable high volume, automated, electronic processes
 that apply to VeriSign (or its computer systems). The compilation,
 repackaging, dissemination or other use of this Data is expressly
 prohibited without the prior written consent of VeriSign. You agree not to
 use electronic processes that are automated and high-volume to access or
 query the Whois database except as reasonably necessary to register
 domain names or modify existing registrations. VeriSign reserves the right
 to restrict your access to the Whois database in its sole discretion to ensure
 operational stability.  VeriSign may restrict or terminate your access to the
 Whois database for failure to abide by these terms of use. VeriSign
 reserves the right to modify these terms at any time.

 The Registry database contains ONLY .COM, .NET, .EDU domains and
 Registrars.
 ")


(def unregistered-response-com
  "
 Whois Server Version 2.0

 Domain names in the .com and .net domains can now be registered
 with many different competing registrars. Go to http://www.internic.net
 for detailed information.

 No match for \"DNDNNDNNDN.COM\".
 >>> Last update of whois database: Sun, 06 Dec 2015 19:25:33 GMT <<<

 NOTICE: The expiration date displayed in this record is the date the
 registrar's sponsorship of the domain name registration in the registry is
 currently set to expire. This date does not necessarily reflect the expiration
 date of the domain name registrant's agreement with the sponsoring
 registrar.  Users may consult the sponsoring registrar's Whois database to
 view the registrar's reported date of expiration for this registration.

 TERMS OF USE: You are not authorized to access or query our Whois
 database through the use of electronic processes that are high-volume and
 automated except as reasonably necessary to register domain names or
 modify existing registrations; the Data in VeriSign Global Registry
 Services' (\"VeriSign\") Whois database is provided by VeriSign for
 information purposes only, and to assist persons in obtaining information
 about or related to a domain name registration record. VeriSign does not
 guarantee its accuracy. By submitting a Whois query, you agree to abide
 by the following terms of use: You agree that you may use this Data only
 for lawful purposes and that under no circumstances will you use this Data
 to: (1) allow, enable, or otherwise support the transmission of mass
 unsolicited, commercial advertising or solicitations via e-mail, telephone,
 or facsimile; or (2) enable high volume, automated, electronic processes
 that apply to VeriSign (or its computer systems). The compilation,
 repackaging, dissemination or other use of this Data is expressly
 prohibited without the prior written consent of VeriSign. You agree not to
 use electronic processes that are automated and high-volume to access or
 query the Whois database except as reasonably necessary to register
 domain names or modify existing registrations. VeriSign reserves the right
 to restrict your access to the Whois database in its sole discretion to ensure
 operational stability.  VeriSign may restrict or terminate your access to the
 Whois database for failure to abide by these terms of use. VeriSign
 reserves the right to modify these terms at any time.

 The Registry database contains ONLY .COM, .NET, .EDU domains and
 Registrars.
 ")

(def registered-response-io
  "
 Domain : soup.io
 Status : Live
 Expiry : 2016-08-07

 NS 1   : ns1.easyname.eu
 NS 2   : ns2.easyname.eu

 Owner  : euphoria GmbH
 Owner  : euphoria GmbH
 Owner  : Fernkorngasse 10
 Owner  : Vienna
 Owner  : AT

 Check for 'soup.ac' --- http://www.nic.ac/go/whois/soup.ac
 Check for 'soup.sh' --- http://www.nic.sh/go/whois/soup.sh

 ")