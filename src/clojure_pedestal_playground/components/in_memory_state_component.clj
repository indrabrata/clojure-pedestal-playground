(ns clojure-pedestal-playground.components.in-memory-state-component
  (:require
   [com.stuartsierra.component :as component]))

(defrecord InMemoryStateComponent
           [config]
  component/Lifecycle

  (start [component]
    (println ";; Starting InMemoryStateComponent")
    ;; the run-time state assoc'd in.
    (assoc component :state-atom (atom [])))

  ;; The double colon :: is shorthand for a namespace-qualified keyword:
  ;; - ::started expands to :current-namespace/started

  (stop [component]
    (println ";; Stopping InMemoryStateComponent")
    (assoc component :state-atom nil)))


(defn new-in-memory-state-component [config]
  (map->InMemoryStateComponent {:config config}))