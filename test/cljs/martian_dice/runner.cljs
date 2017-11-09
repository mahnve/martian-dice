(ns martian-dice.runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [martian-dice.game.game-test]
            [martian-dice.game.dice-test]))


(doo-tests 'martian-dice.game.game-test
           'martian-dice.game.dice-test)

