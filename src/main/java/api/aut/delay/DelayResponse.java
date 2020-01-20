package api.aut.delay;

import lombok.Getter;

import java.util.Objects;

@Getter
public class DelayResponse {
    String url;

    public DelayResponse(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DelayResponse that = (DelayResponse) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
