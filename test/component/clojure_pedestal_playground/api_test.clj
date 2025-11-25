(ns component.clojure-pedestal-playground.api-test
  (:require
   [clj-http.client :as client]
   [clojure-pedestal-playground.components.pedestal-component :refer [url-for]]
   [clojure-pedestal-playground.core :as core]
   [clojure.string :as str]
   [clojure.test :refer [deftest is testing]]
   [com.stuartsierra.component :as component])
  (:import
   [java.net ServerSocket]))

(defn sut->url
  [sut path]
  ()
  (str/join ["http://localhost:"
             (-> sut :pedestal-component :config :server :port)
             path]))

(defn get-free-port
  []
  (with-open [socket (ServerSocket. 0)]
    (.getLocalPort socket)))

(deftest simple-api-test
  (testing "Simple API Test"
    (is (= 1 1))))

(defmacro with-system
  [[system-sym system-expr] & body]
  `(let [~system-sym (component/start ~system-expr)]
     (try
       ~@body
       (finally
         (component/stop ~system-sym)))))

(deftest greeting-test
  #_{:clj-kondo/ignore [:unresolved-symbol]}
  (with-system
    [sut (core/app-system {:server {:port (get-free-port)}})]
    (is (= {:body "Hello, world!\n"
            :status 200}
           (-> (sut->url sut (url-for :greet))
               (client/get)
               (select-keys [:body :status]))))))


(deftest get-todo-test
  #_{:clj-kondo/ignore [:unresolved-symbol]}
  (let [random-id (random-uuid)
        todo {:id random-id
              :name "My Todo"
              :items [{:id (random-uuid) :name "Finish clojure tutorial"}]}]

    (with-system
      [sut (core/app-system {:server {:port (get-free-port)}})]
      (reset! (-> sut :in-memory-state-component :state-atom) [todo]))
    (testing "Testing exist todo")
    (is (= {:body (pr-str todo)
            :status 200}
           (-> (sut->url sut (url-for :get-todo {:path-params {:todo-id random-id}}))
               (client/get)
               (select-keys [:body :status]))))))

  