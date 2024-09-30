package com.imepac.ads.artmanagement.controllers;

import com.imepac.ads.artmanagement.dto.ArtworkDto;
import com.imepac.ads.artmanagement.entities.ArtworkEntity;
import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.util.List;

@RestController
@RequestMapping("v1/artworks")
public class ArtworkController {

    private DynamoDbTemplate dynamoDbTemplate;

    public ArtworkController(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @PostMapping
    public ResponseEntity<Void> saveArtwork(@RequestBody ArtworkDto artworkDto) {
        var entity = new ArtworkEntity();
        entity.getArtworkEntity(artworkDto);
        dynamoDbTemplate.save(entity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{artwork_id}")
    public ResponseEntity<ArtworkEntity> listArtwork(@PathVariable("artwork_id") String artwork_id){
            var key = Key.builder().partitionValue(artwork_id).build();

            var condition = QueryConditional.keyEqualTo(key);

            var query = QueryEnhancedRequest.builder().queryConditional(condition).build();

            try {
                var artwork = dynamoDbTemplate.query(query, ArtworkEntity.class);
                ArtworkEntity artworks = (ArtworkEntity) artwork.items().stream().toList();
                return ResponseEntity.ok(artworks);
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
    }

    @DeleteMapping("/{artwork_id}")
    public ResponseEntity<Void> deleteArtwork(@PathVariable String artwork_id){
        var key = Key.builder().partitionValue(artwork_id).build();
        var condition = QueryConditional.keyEqualTo(key);
        var query = QueryEnhancedRequest.builder().queryConditional(condition).build();

        try{
            dynamoDbTemplate.delete(query);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
