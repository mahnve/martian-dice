(ns martian-dice.game.game-test
  (:require [martian-dice.game.game :as game]
            [cljs.test :refer-macros [deftest is testing]]
            [clojure.test.check.generators :as gen]
            [clojure.spec.test.alpha :as stest]
            [martian-dice.game.dice :as dice]))

(stest/check `game/roll-dice)

(defn test-game []
  {::game/current-player "Foo Bar"
   ::game/new-turn true
   ::game/no-of-dice 14
   ::game/selected-dice nil
   ::game/saved-dice {::dice/cow 3
                      ::dice/human 0
                      ::dice/chicken 0
                      ::dice/blast 4
                      ::dice/spaceship 0}
   ::game/latest-roll {::dice/cow 2
                       ::dice/human 0
                       ::dice/chicken 1
                       ::dice/blast 2
                       ::dice/spaceship 2}
   ::game/players [{:name "Foo Bar"
                    :score 0}]})

(defn test-game-with-selected []
  {::game/current-player "Foo Bar"
   ::game/new-turn true
   ::game/no-of-dice 14
   ::game/selected-dice {::dice/cow 5
                         ::dice/blast 3}

   ::game/saved-dice {::dice/cow 3
                      ::dice/human 0
                      ::dice/chicken 0
                      ::dice/blast 4
                      ::dice/spaceship 0}
   ::game/latest-roll {::dice/cow 2
                       ::dice/human 0
                       ::dice/chicken 1
                       ::dice/blast 2
                       ::dice/spaceship 2}
   ::game/players [{:name "Foo Bar"
                    :score 0}]})
(deftest test-count-blasts
  (is (= 4 (game/no-of-blasts (test-game)))))

(deftest test-no-saved-dice
  (is (= 7 (game/no-of-saved-dice (test-game)) )))

(deftest test-no-of-dice-to-roll
  (testing "just blasts"
    (is (= 7 (game/no-of-dice-to-roll (test-game))))))

(deftest test-clear-selected-dice
  (is (= nil (-> (test-game-with-selected) game/clear-selected-dice ::game/selected-dice))))
