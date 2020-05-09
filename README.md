## cljla

Solutions to Clojure challenges for the [Los Angeles Clojure Users Group](https://www.meetup.com/Los-Angeles-Clojure-Users-Group/).

## Project Tooling

Easiest way to build project is to use Makefile (make utility). Run `make help` for additional info.

Available commands:

* clean         - clear target folder
* javac         - compile java sources
* compile       - compile clojure code
* jar           - build jar file (as library)
* uberjar       - build executable jar file with all dependencies (uberjar)
* install       - install jar file (library) to local .m2
* deploy        - deploy jar file (library) to clojars.org
* conflicts     - show class conflicts (same name class in multiple jar files)
* standalone    - create standalone executable bundle with custom JDK 9+
* release       - release artifact (if `git flow` model is not used). To release artifact run `clojure -A:pbuild release`.
* bump          - bump version artifact in build file. E.g: `clojure -A:pbuilder bump beta`. Parameter should be
one of: major, minor, patch, alpha, beta, rc, qualifier, release

## Tests

To run tests use `clojure -A:test` or `make test`. Additional optional parameter may be :unit or :integration
e.g. `clojure -A:test :unit` or `make test :integration`.


## License

Copyright © 2020 Ticean Bennett

Distributed under the Eclipse Public License 2.0 or (at your option) any later version.
