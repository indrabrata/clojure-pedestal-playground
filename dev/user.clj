(ns user
  "Development namespace with REPL utilities"
  #_{:clj-kondo/ignore [:unused-referred-var]}
  (:require
   ;; Need to define this function so we can execute from repl
   [com.stuartsierra.component.repl :refer [reset set-init start stop system]]
   [clojure-pedestal-playground.core :as core]
   [clojure-pedestal-playground.config :as config]))

;; Tell component.repl how to create your system
(set-init (fn [_] (core/app-system (config/read-config))))

(println "\n===========================================")
(println "Dev utilities loaded. Available commands:")
(println "===========================================")
(println "  (start)   - Start the component system")
(println "  (stop)    - Stop the component system")
(println "  (reset)   - Stop, reload code, and restart")
(println "  (system)  - Show current system state")
(println "  (refresh) - Reload changed namespaces")
(println "===========================================\n")
