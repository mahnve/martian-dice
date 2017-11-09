(ns martian-dice.game.dice-test
  (:require [martian-dice.game.dice :as dice]
            [cljs.test :refer-macros [deftest is testing]]
            [cljs.spec.test.alpha :as stest]))

(deftest dice
  (stest/instrument 'dice/roll-die)
  (stest/check 'dice/roll-die))

