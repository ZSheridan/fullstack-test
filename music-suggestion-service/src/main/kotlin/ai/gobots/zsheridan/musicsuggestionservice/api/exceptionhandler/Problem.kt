package ai.gobots.zsheridan.musicsuggestionservice.api.exceptionhandler

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.OffsetDateTime

class Field (val parameterName: String,
             val message: String?)

@JsonInclude(JsonInclude.Include.NON_NULL)
class Problem (val status: Int? = null,
               val title: String? = null,
               val dateTime: OffsetDateTime? = null,
               val fields: List<Field>? = null)