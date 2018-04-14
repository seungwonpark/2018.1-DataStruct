public class MovieDBItem implements Comparable<MovieDBItem> {

    private final String genre;
    private final String title;

    public MovieDBItem(String genre, String title) {
        if (genre == null) throw new NullPointerException("genre");
        if (title == null) throw new NullPointerException("title");

        this.genre = genre;
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(MovieDBItem other) {
        if(this == null || this.genre == null || this.title == null){
            throw new NullPointerException();
        }
        if(other == null || other.genre == null || other.title == null){
            throw new NullPointerException();
        }
        // first, compare genre.
        int comparedValue = this.genre.compareTo(other.genre);
        if(comparedValue == 0){ // if genre is identical,
            // then compare title.
            comparedValue = this.title.compareTo(other.title);
        }
        return comparedValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (this == null || obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MovieDBItem other = (MovieDBItem) obj;
        if (genre == null) {
            if (other.genre != null)
                return false;
        } else if (!genre.equals(other.genre))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((genre == null) ? 0 : genre.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

}
