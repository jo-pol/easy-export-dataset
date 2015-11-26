/*******************************************************************************
  * Copyright 2015 DANS - Data Archiving and Networked Services
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *   http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  ******************************************************************************/

package nl.knaw.dans.easy.export

import java.io.File

import org.apache.commons.io.FileUtils._
import org.scalatest.matchers.{MatchResult, Matcher}

/** See also <a href="http://www.scalatest.org/user_guide/using_matchers#usingCustomMatchers">CustomMatchers</a> */
trait CustomMatchers {

  class ContentMatcher(content: String) extends Matcher[File] {
    def apply(left: File) = {
      def trimLines(s: String): String = s.split("\n").map(_.trim).mkString("\n")
      MatchResult(
        trimLines(readFileToString(left)).contains(trimLines(content)),
        s"$left did not contain: $content" ,
        s"$left contains $content"
      )
    }
  }

  /** usage example: new File(...) should containTrimmed("...") */
  def containTrimmed(content: String) = new ContentMatcher(content)
}

object CustomMatchers extends CustomMatchers