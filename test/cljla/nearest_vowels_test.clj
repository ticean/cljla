(ns cljla.nearest-vowels-test
  "[Distance to nearest vowel](https://purelyfunctional.tv/issues/purelyfunctional-tv-newsletter-376-learn-from-core/)

  Write a function that takes a string as argument. Each character in the string
  will be a letter. The function should return a sequence containing the
  distances from each corresponding letter in the string to the nearest vowel in
  the string.

  For example:

  (nearest-vowels \"aeiou\") ;=> [0 0 0 0 0]  ;; if the letter is a vowel, the distance is 0
  (nearest-vowels \"babbb\" ;=> [1 0 1 2 3]
  (nearest-vowels \"babbba\") ;=> [1 0 1 2 1 0]

  Notes:

      All input strings will contain at least one vowel and all letters.
      Vowels are a, e, i, o, and u.
  "
  (:require
    [clojure.test :refer [deftest testing is]]
    [cljla.nearest-vowels :as subj]))


(deftest ^:unit nearest-vowels-test
  (is (= [0 0 0 0 0] (subj/nearest-vowels "aeiou")))
  (is (= [1 0 1 2 3] (subj/nearest-vowels "babbb")))
  (is (= [1 0 1 2 1 0] (subj/nearest-vowels "babbba"))))
