(ns unit.clojure-pedestal-playground.simple-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure-pedestal-playground.components.pedestal-component :refer [url-for]]))

(deftest simple-unit-test
  (testing "Simple Unit Test"
    (is (= 1 1))))

(deftest url-for-test
  (testing "greet endpoint url")
  (is (= "/greet" (url-for :greet))))
;; :greet -> :route-name

(deftest get-todo-test
  (testing "get todo endpoint url")
  (let [random-id (random-uuid)]
    (is (= (str "/todo" random-id) (url-for :get-todo {:path-params {:todo-id random-id}})))))