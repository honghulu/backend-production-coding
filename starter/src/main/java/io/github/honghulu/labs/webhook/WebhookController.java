package io.github.honghulu.labs.webhook;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhooks/provider")
public class WebhookController {
    @PostMapping
    public ResponseEntity<Void> receive(@RequestHeader("X-Signature") String signature, @RequestBody String body) {
        // TODO LAB 05: signature verification + durable dedupe + safe processing semantics.
        throw new UnsupportedOperationException("TODO: implement webhook receiver");
    }
}
