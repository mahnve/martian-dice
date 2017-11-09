(ns martian-dice.game.game-test
  (:require [martian-dice.game.game :as game]
            [cljs.test :refer-macros [deftest is testing]]
            [clojure.spec.test.alpha :as stest]))

(deftest base
  (testing "whatever"
    (is (= 1 1))))

