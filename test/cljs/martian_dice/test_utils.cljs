(ns martian-dice.test-utils
  (:require [clojure.pprint :as pprint]
            [cljs.spec.test.alpha :as stest]))

(defn summarize-results [spec-check]
  (map (comp #(pprint/write % :stream nil) stest/abbrev-result) spec-check))

(defn valid-check? [spec-check]
  (is (nil? (-> spec-check first :failure)) (summarize-results spec-check)))

