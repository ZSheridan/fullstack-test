package ai.gobots.zsheridan.frontendapplication.exceptionhandler

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.OffsetDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
class Problem (val status: Int? = null,
               val title: String? = null,
               val dateTime: OffsetDateTime? = null)