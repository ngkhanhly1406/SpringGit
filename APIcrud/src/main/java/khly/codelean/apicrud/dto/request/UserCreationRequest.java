package khly.codelean.apicrud.dto.request;

import java.time.LocalDateTime;

public class UserCreationRequest {
    private String title;       // Tiêu đề bài viết
    private String content;     // Nội dung bài viết
    private Double rate;        // Đánh giá của bài viết
    private LocalDateTime created; // Ngày tạo bài viết
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
