(ns clojure-pedestal-playground.components.pedestal-component
  (:require [com.stuartsierra.component :as component]
            [io.pedestal.connector :as conn]
            [io.pedestal.http.http-kit :as hk]))

(defn greet-handler [_request]
  {:status 200
   :body   "Hello, world!\n"})

(def routes
  #{["/greet" :get greet-handler :route-name :greet]})

;; To show how other component depends
(defrecord PedestalComponent
           [config example-component]
  component/Lifecycle

  (start [component]
    (println ";; Starting PedestalComponent")
    (let [server (-> (conn/default-connector-map (-> config :server :port))
                     (conn/with-default-interceptors)
                     (conn/with-routes routes)
                     (hk/create-connector nil)
                     (conn/start!))]
      ;; the run-time state assoc'd in.
      (assoc component :server server)))


  ;; The double colon :: is shorthand for a namespace-qualified keyword:
  ;; - ::started expands to :current-namespace/started

  (stop [component]
    (println ";; Stopping PedestalComponent")
    (when-let [server (:server component)]
      (conn/stop! server))
    (assoc component :server nil)))


(defn new-pedestal-component [config]
  (map->PedestalComponent {:config config}))


;; The correct equivalent expressions would be for accessing map:
;; - (:port (:server config)) - nested map access
;; - (get-in config [:server :port]) - using get-in for nested access
;; - (-> config :server :port) - threading macro