package model

import com.github.tminglei.slickpg.{ExPostgresProfile, PgArraySupport, PgDate2Support, PgDateSupport}

trait PostgresProfile
  extends ExPostgresProfile
          with PgArraySupport
          with PgDateSupport
          with PgDate2Support {

  object API
    extends super.API
            with ArrayImplicits
            with SimpleDateTimeImplicits
            with DateTimeImplicits
            with Date2DateTimePlainImplicits

  override val api: API = API
}
