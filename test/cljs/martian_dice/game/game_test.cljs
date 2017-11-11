(ns martian-dice.game.game-test
  (:require [martian-dice.game.game :as game]
            [cljs.test :refer-macros [deftest is testing]]
            [clojure.test.check.generators :as gen]
            [clojure.spec.test.alpha :as stest]))

(stest/check `game/roll-dice)

