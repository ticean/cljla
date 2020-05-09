(ns ticean.cljla.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [matcho.core :refer [match]]
            [ticean.cljla.core :as sut]))


(deftest ^:unit a-test
  (testing "simple test."
    (is (= 1 1))))
