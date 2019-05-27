package generic.factory;

import org.springframework.data.repository.query.parser.Part;

public interface Factory<T> {
    T create();
}
