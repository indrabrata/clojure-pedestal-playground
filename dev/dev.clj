(ns dev
  (:require [com.stuartsierra.component.repl :as component-repl]
            [clojure-pedestal-playground.core :as core]))

(component-repl/set-init
 (fn [_old-system]
   (core/app-system {:server {:port 8080}})))

;; To running in this namespace use (component-repl/reset)