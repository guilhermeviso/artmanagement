package com.imepac.ads.artmanagement.entities;

import com.imepac.ads.artmanagement.dto.ArtworkDto;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class ArtworkEntity {

    private String artwork_id;
    private String artist_name;
    private String art_title;
    private String medium;
    private int year_created;
    private double price;
    private String description;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("ArtworkID")
    public String getArtwork_id() {
        return artwork_id;
    }

    public void setArtwork_id(String artwork_id) {
        this.artwork_id = artwork_id;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("ArtistName")
    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    @DynamoDbAttribute("Title")
    public String getArt_title() {
        return art_title;
    }

    public void setArt_title(String art_title) {
        this.art_title = art_title;
    }

    @DynamoDbAttribute("Medium")
    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
    @DynamoDbAttribute("YearCreated")
    public int getYear_created() {
        return year_created;
    }

    public void setYear_created(int year_created) {
        this.year_created = year_created;
    }

    @DynamoDbAttribute("Price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    @DynamoDbAttribute("Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArtworkEntity getArtworkEntity(ArtworkDto artworkDto) {
        ArtworkEntity artworkEntity = new ArtworkEntity();
        artworkEntity.setArtwork_id(artworkDto.artwork_id());
        artworkEntity.setArtist_name(artworkDto.artist_name());
        artworkEntity.setArt_title(artworkDto.title());
        artworkEntity.setMedium(artworkDto.medium());
        artworkEntity.setYear_created(artworkDto.year());
        artworkEntity.setPrice(artworkDto.price());
        artworkEntity.setDescription(artworkDto.description());
        return artworkEntity;
    }
}
