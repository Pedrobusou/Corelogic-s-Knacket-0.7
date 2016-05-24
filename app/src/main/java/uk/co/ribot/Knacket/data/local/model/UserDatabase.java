package uk.co.ribot.Knacket.data.local.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import uk.co.ribot.Knacket.data.model.User;

@DatabaseTable(tableName = "user")
public class UserDatabase extends BaseTable{
    public static final String COLUMN_SERVER_ID = "server_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_CREATED_AT = "created_at";
    public static final String COLUMN_UPDATED_AT = "updated_at";

    @DatabaseField(columnName = COLUMN_SERVER_ID)
    String serverId;

    @DatabaseField(columnName = COLUMN_NAME)
    String name;

    @DatabaseField(columnName = COLUMN_EMAIL)
    String email;

    @DatabaseField(columnName = COLUMN_RATING)
    String rating;

    @DatabaseField(columnName = COLUMN_CREATED_AT)
    String created_at;

    @DatabaseField(columnName = COLUMN_UPDATED_AT)
    String updated_at;

    public UserDatabase() {}

    public UserDatabase(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.created_at = user.getCreated_at();
        this.updated_at = user.getUpdated_at();
        this.serverId = user.getId();
        this.rating = user.getRating();
    }

    public UserDatabase(String name, String email, String created_at, String updated_at, String id, String rating) {
        this.name = name;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.serverId = id;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String id) {
        this.serverId = id;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
