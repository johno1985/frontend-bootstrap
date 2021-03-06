/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.play.frontend.filters

import uk.gov.hmrc.crypto.{ApplicationCrypto, Crypted, PlainText}
import uk.gov.hmrc.play.filters.frontend.CookieCryptoFilter

object SessionCookieCryptoFilter extends CookieCryptoFilter {

  // Lazy because the filter is instantiated before the config is loaded
  private lazy val crypto = ApplicationCrypto.SessionCookieCrypto

  override protected val encrypter = encrypt _
  override protected val decrypter = decrypt _

  def encrypt(plainCookie: String): String = crypto.encrypt(PlainText(plainCookie)).value

  def decrypt(encryptedCookie: String): String = crypto.decrypt(Crypted(encryptedCookie)).value

}
