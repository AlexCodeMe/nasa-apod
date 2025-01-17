package com.alexcasey.nasa_apod.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Apod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String copyright;
    private LocalDate date;
    private String explanation;
    private String hdurl;
    private String mediaType;
    private String serviceVersion;
    private String title;
    private String url;
}

/**
 * "copyright": "Laszlo Bagi",
 * "date": "2017-05-20",
 * "explanation": "Big, beautiful spiral galaxy M101 is one of the last entries
 * in Charles Messier's famous catalog, but definitely not one of the least.
 * About 170,000 light-years across, this galaxy is enormous, almost twice the
 * size of our own Milky Way galaxy. M101 was also one of the original spiral
 * nebulae observed by Lord Rosse's large 19th century telescope, the Leviathan
 * of Parsontown. M101 shares this modern telescopic field of view with spiky
 * foreground stars within the Milky Way, and more distant background galaxies.
 * The colors of the Milky Way stars can also be found in the starlight from the
 * large island universe. Its core is dominated by light from cool yellowish
 * stars. Along its grand spiral arms are the blue colors of hotter, young stars
 * mixed with obscuring dust lanes and pinkish star forming regions. Also known
 * as the Pinwheel Galaxy, M101 lies within the boundaries of the northern
 * constellation Ursa Major, about 25 million light-years away.",
 * "hdurl": "https://apod.nasa.gov/apod/image/1705/M101p2000bagi.jpg",
 * "media_type": "image",
 * "service_version": "v1",
 * "title": "A View Toward M101",
 * "url": "https://apod.nasa.gov/apod/image/1705/M101p1024bagi.jpg"
 */