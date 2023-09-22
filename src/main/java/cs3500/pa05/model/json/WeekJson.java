package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Week;

/**
 * represents a way to communicate a week
 *
 * @param week the week
 */
public record WeekJson(
    @JsonProperty("week") Week week) {
}
