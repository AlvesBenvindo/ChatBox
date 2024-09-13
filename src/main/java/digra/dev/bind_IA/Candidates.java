package digra.dev.bind_IA;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Candidates {
    @SerializedName("content")
    public static Content content;
    String finishReason;
    int index;
    // ... outros campos

    static class Content {
        public List<Part> parts;
        public String role;
    }

    static class Part {
        public String text;
    }
}



