(ns clojure-pedestal-playground.components.example-component
  (:require [com.stuartsierra.component :as component]))

(defrecord ExampleComponent
           [config]
  component/Lifecycle

  (start [component]
    (println ";; Starting ExampleComponent")
    ;; the run-time state assoc'd in.
    (assoc component :state ::started))

  ;; The double colon :: is shorthand for a namespace-qualified keyword:
  ;; - ::started expands to :current-namespace/started

  (stop [component]
    (println ";; Stopping ExampleComponent")
    (assoc component :state nil)))

(defn new-example-component [config]
  (map->ExampleComponent {:config config}))
