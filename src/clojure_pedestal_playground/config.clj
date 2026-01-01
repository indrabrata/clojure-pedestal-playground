(ns clojure-pedestal-playground.config
  (:require
   [aero.core :as aero]
   [clojure.java.io :as io]))

;; (defn read-config
;;   []
;;   (aero/read-config (io/resource "config.edn")))

;; but it does use the -> thread-first macro inside it.
(defn read-config
  []
  (-> "config.edn"
      (io/resource)
      (aero/read-config)))

;; When to switch namespace use:
;; (in-ns 'clojure-pedestal-playground.config)

;; Notice the single quote ' before the namespace name. Without the quote, Clojure tries to evaluate clojure-pedestal-playground.config as a variable or class, which
;;  causes the ClassNotFoundException.

;; If the function want to evaluate not exists
;; (in-ns 'clojure-pedestal-playground.config)
;; (clojure.core/require 'clojure-pedestal-playground.config :reload)
;; (read-config)
;; OR
;; ctrl + alt + c (evaluate using calva)
