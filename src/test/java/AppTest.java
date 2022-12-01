import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.example.Answer;
import org.example.Category;
import org.example.Tags;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {

    private static final OkHttpClient CLIENT = new OkHttpClient();

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    ObjectMapper objectMapper = new ObjectMapper();

    private String getJson(int id , Category category , String name , List<String> photoUrl , List<org.example.Tags> tags , String status) throws JsonProcessingException {

        Answer answer = new Answer(id , category , name , photoUrl , tags , status);
        return objectMapper.writeValueAsString(answer);
    }

    @Test
    @Order(20)
    public void postPet() throws IOException {

        int id = 456345;
        Category category = new Category(5 , "MyDoggo");
        String name = "Forex";
        org.example.Tags tags = new org.example.Tags(2 , "Best");
        String photoUrl  = "MyUrl";
        String status = "good";
        List<String> photoUrlList = new ArrayList<>();
        photoUrlList.add(photoUrl);
        List<org.example.Tags> tagsList = new ArrayList<>();
        tagsList.add(tags);

        RequestBody requestBody = RequestBody.create(AppTest.JSON , getJson(id , category , name , photoUrlList , tagsList , status));

        Request request = new Request.Builder().url("https://petstore.swagger.io/v2/pet/").post(requestBody).build();

        Response content;

        try {
            content = Objects.requireNonNull((AppTest.CLIENT.newCall(request).execute()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Answer entity = objectMapper.readValue(Objects.requireNonNull(content.body()).string(), Answer.class);

        List<org.example.Tags> entityTags = new ArrayList<>(entity.getTags());
        List<org.example.Tags> sendTags = new ArrayList<>(tagsList);

        Assertions.assertEquals(entity.getId() , id);
        Assertions.assertEquals(entity.getCategory() , category);
        Assertions.assertEquals(entity.getName() , name);
        Assertions.assertEquals(entity.getPhotoUrls() , photoUrlList);
        Assertions.assertArrayEquals(entityTags.toArray() , sendTags.toArray());
        Assertions.assertEquals(entity.getStatus() , status);
        Assertions.assertEquals(content.code() , 200);
    }


    @Test
    @Order(10)
    public void petById() throws IOException {

        int id = 4564445;
        Category category = new Category(5 , "MyDoggo");
        String name = "Forex";
        org.example.Tags tags = new org.example.Tags(2 , "Best");
        String photoUrl  = "MyUrl";
        String status = "good";
        List<String> photoUrlList = new ArrayList<>();
        photoUrlList.add(photoUrl);
        List<org.example.Tags> tagsList = new ArrayList<>();
        tagsList.add(tags);

        RequestBody requestBody = RequestBody.create(AppTest.JSON , getJson(id , category , name , photoUrlList , tagsList , status));

        Request request = new Request.Builder().url("https://petstore.swagger.io/v2/pet/").post(requestBody).build();

        AppTest.CLIENT.newCall(request).execute();

        request = new Request.Builder().url("https://petstore.swagger.io/v2/pet/" + id).build();

        Response content;

        try {
            content = Objects.requireNonNull((AppTest.CLIENT.newCall(request).execute()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Answer entity = objectMapper.readValue(Objects.requireNonNull(content.body()).string(), Answer.class);

        List<org.example.Tags> entityTags = new ArrayList<>(entity.getTags());
        List<org.example.Tags> sendTags = new ArrayList<>(tagsList);

        Assertions.assertEquals(entity.getId() , id);
        Assertions.assertEquals(entity.getCategory() , category);
        Assertions.assertEquals(entity.getName() , name);
        Assertions.assertEquals(entity.getPhotoUrls() , photoUrlList);
        Assertions.assertArrayEquals(entityTags.toArray() , sendTags.toArray());
        Assertions.assertEquals(entity.getStatus() , status);
        Assertions.assertEquals(content.code() , 200);
    }

    @Test
    @Order(30)
    public void updatePet() throws IOException {

        int id = 456345;
        Category category = new Category(5 , "MyDoggo");
        String name = "Santa";
        org.example.Tags tags = new org.example.Tags(2 , "Best");
        String photoUrl  = "MyUrl";
        String status = "good";
        List<String> photoUrlList = new ArrayList<>();
        photoUrlList.add(photoUrl);
        List<org.example.Tags> tagsList = new ArrayList<>();
        tagsList.add(tags);

        RequestBody requestBody = RequestBody.create(AppTest.JSON , getJson(id , category , name , photoUrlList , tagsList , status));

        Request request = new Request.Builder().url("https://petstore.swagger.io/v2/pet").put(requestBody).build();

        Response content;

        try {
            content = Objects.requireNonNull((AppTest.CLIENT.newCall(request).execute()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Answer entity = objectMapper.readValue(Objects.requireNonNull(content.body()).string(), Answer.class);

        List<org.example.Tags> entityTags = new ArrayList<>(entity.getTags());
        List<Tags> sendTags = new ArrayList<>(tagsList);

        Assertions.assertEquals(entity.getId() , id);
        Assertions.assertEquals(entity.getCategory() , category);
        Assertions.assertEquals(entity.getName() , name);
        Assertions.assertEquals(entity.getPhotoUrls() , photoUrlList);
        Assertions.assertArrayEquals(entityTags.toArray() , sendTags.toArray());
        Assertions.assertEquals(entity.getStatus() , status);
        Assertions.assertEquals(content.code() , 200);
    }


}
