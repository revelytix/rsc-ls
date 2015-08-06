# rsc-ls

Leiningen plugin to list the contents of the resource directories in a file called LS-DIR.

It's Leiningen, and for the JVM, so you're probably only looking at this if you do Clojure.

## Rationale

Have you ever had a directory full of data files that you want to access at runtime?
Dragging the directory along isn't usually an option, so you just throw it into the
resources directory and voilà, they are all there.

But how do you find those files? If you have the path for any file then you can just load
it up as a resource, but that means that every single file has to be explicitly listed in
your source code. If that doesn't bother you, then just imagine your manager suddenly
complaining because they added a new file to the directory, and it didn't magically show
up in the part of the application that lists all the data.
(Personally, I don't have to imagine it)

This is when you bring `rsc-ls` into your project. Add it to the build process, and it
automatically scans the resource paths, to build a directory listing in a central resource
file. So now all the files can be found in one central place, and you no longer need to
list any others.

## Usage

Refer to the plugin in the project.clj file:

`:plugins [[org.clojars.quoll/rsc-ls "0.0.3"]]`

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
