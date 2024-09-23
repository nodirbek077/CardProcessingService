package uz.asgardia;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        String string = UUID.randomUUID().toString();
        System.out.println(string);


    }

    @GetMapping(value = "/{id}/custom-etag")
    public ResponseEntity<?>
    findByIdWithCustomEtag(@PathVariable("id") final Long id) {

        // ...Foo foo = ...

//        return ResponseEntity.ok().eTag(Long.toString(1)).body(null);
//        return ResponseEntity.ok().eTag()
        return null;
    }
}
