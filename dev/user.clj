(ns user
  "Development namespace with REPL utilities"
  (:require [com.stuartsierra.component :as component]
            [com.stuartsierra.component.repl :refer [reset set-init start stop system]]
            [clojure.tools.namespace.repl :refer [refresh]]
            [clojure-pedestal-playground.components.example-component :as example]))

(defn dev-system
  "Constructs a system map for development."
  []
  (component/system-map
   :example (example/new-example-component {:server {:port 8080}})))

;; Tell component.repl how to create your system
(set-init (fn [_] (dev-system)))

(println "\n===========================================")
(println "Dev utilities loaded. Available commands:")
(println "===========================================")
(println "  (start)   - Start the component system")
(println "  (stop)    - Stop the component system")
(println "  (reset)   - Stop, reload code, and restart")
(println "  (system)  - Show current system state")
(println "  (refresh) - Reload changed namespaces")
(println "===========================================\n")
