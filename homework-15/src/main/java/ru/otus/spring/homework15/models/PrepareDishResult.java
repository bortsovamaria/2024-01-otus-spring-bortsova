
package ru.otus.spring.homework15.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrepareDishResult {

    private CookResult cookResult;

    private boolean hasReady = false;

    public PrepareDishResult(CookResult cookResult) {
        this.cookResult = cookResult;
    }
}