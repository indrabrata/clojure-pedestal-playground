;; credit : https://www.youtube.com/@andrey.fadeev

(ns clojure-pedestal-playground.core
  (:gen-class)
  (:require
   [clojure-pedestal-playground.components.example-component :as example-component]
   [clojure-pedestal-playground.components.in-memory-state-component :as in-memory-state-component]
   [clojure-pedestal-playground.components.pedestal-component :as pedestal-component]
   [clojure-pedestal-playground.config :as config]
   [com.stuartsierra.component :as component]))

(defn app-system
  [config]
  (component/system-map
   :example-component (example-component/new-example-component config)
   :in-memory-state-component (in-memory-state-component/new-in-memory-state-component config)
   :pedestal-component
   (component/using
    (pedestal-component/new-pedestal-component config)
    [:example-component
     :in-memory-state-component])))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [system (-> config/read-config
                   (app-system)
                   (component/start-system))]
    (println "Starting clojure pedestal app with config!")
    (.addShutdownHook
     (Runtime/getRuntime)
     (new Thread #(component/stop-system system)))))


;; Note :

;; clj -M -m clojure-pedestal-playground.core
;; The -m flag specifies a namespace to run
;; Looks for a namespace clojure-pedestal-playground.core (typically in src/clojure_pedestal_playground/core.clj)
;; Executes the -main function in that namespace

;; (defn print-args
;;   "Argument (args) is a map"
;;   [args]
;;   (println "Arguments : " args))

;; clj -X clojure-pedestal-playground.core/print-args <arguments>
