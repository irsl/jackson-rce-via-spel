# jackson-rce-via-spel
An example project that exploits the default typing issue in Jackson-databind (https://github.com/FasterXML/jackson-databind)
via Spring application contexts and expressions

# Context
The Jackson-databind project has a feature called default-typing (not enabled by default). When the target class has some 
polymorph fields inside (such as interfaces, abstract classes or the Object base class), the library can include type info
into the JSON structure and use that info at unmarshalling. This  can be dangerous when the input is controlled by an 
attacker and the target class contains a field of type Object or something general (like Comparable).

How likely is this? I'm naive, so I hope Java developers don't degrade a type-safe language to the level of an interpreted
type-unsafe language by (ab)using Objects as base classes... But I wouldn't be surprised if one day some huge enterprise 
software would be exploited one day via this vulnerability.

After the original discoveries (CVE-2017-7525) had been reported, the author patched this attack surface with a blacklist, 
which was incomplete (as by nature of blacklists). This proof-of-concept project is a follow-up to demonstrate one more
way of exploitation; by abusing Spring classes via Jackson, this could lead to remote code execution. Note: 
FileSystemXmlApplicationContext is happy to fetch the specified Spring context from anywhere, even from remote location 
via http.

MITRE assigned CVE-2017-17485 to this vulnerability.

# Affected versions

The following ones (inclusive) and older: 2.9.3, 2.7.9.1, 2.8.10

# Mitigation

The fixed versions 2.7.9.2, 2.8.11 and 2.9.3.1 (which is to be released at time of writing these lines) expanded
the blacklist once again so that Spring application contexts cannot be instantiated anymore.

The new major version (3.x) of Jackson-databind will address this topic via a new API layer that provides a way
to achieve whitelisting-based serialization for these polymorph classes.

# References

https://medium.com/@cowtowncoder/on-jackson-cves-dont-panic-here-is-what-you-need-to-know-54cd0d6e8062
https://adamcaudill.com/2017/10/04/exploiting-jackson-rce-cve-2017-7525/
