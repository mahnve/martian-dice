(ns martian-dice.game.dice-test
  (:require [martian-dice.game.dice :as dice]
            [cljs.test :refer-macros [deftest is testing]]
            [clojure.test.check.clojure-test :refer-macros [defspec]]
            [clojure.pprint :as pprint]
            [cljs.spec.test.alpha :as stest]))

(defn summarize-results' [spec-check]
  (map (comp #(pprint/write % :stream nil) stest/abbrev-result) spec-check))

(defn valid-check? [spec-check]
  (is (nil? (-> spec-check first :failure)) (summarize-results' spec-check)))

(deftest test-die
  (is (valid-check? (stest/check `dice/roll-die))))

(deftest test-dice
  (is (valid-check? (stest/check `dice/roll-dice))))
