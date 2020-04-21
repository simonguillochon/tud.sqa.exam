package tud.sqa.exam.Survey;

import lombok.NonNull;

public class Survey {
    @NonNull
    private final Integer id;
    @NonNull
    private final String name;

    public Survey(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
