# rsc-ls

Leiningen plugin to list the contents of the resource directories in a file called LS-DIR.

## Usage

Refer to the plugin in the project.clj file:

`:plugins [[org.clojars.quoll/rsc-ls "0.0.2"]]`

Operates as a target, so it can invoked from the command line:

`$ lein rsc-ls`

Alternatively, it can be placed in the prep-tasks list. For example:

`:prep-tasks ["javac" "compile" "rsc-ls"]`

The file is placed in the resource directory, or the first resource directory if there is more than one. The file can be read as a resource at runtime. The contents are all the resource files, separated by newline characters.

The following example returns all the resource files in a seq:

`(let [file-resource (clojure.java.io/resource "LS-DIR")]
   (clojure.string/split #"\n" (slurp file-resource)))`

## License

Copyright © 2013 Revelytix

Distributed under the Eclipse Public License, the same as Clojure.
