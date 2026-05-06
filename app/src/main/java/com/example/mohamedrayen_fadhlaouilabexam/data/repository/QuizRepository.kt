package com.example.mohamedrayen_fadhlaouilabexam.data.repository

import com.example.mohamedrayen_fadhlaouilabexam.R
import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Category
import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Difficulty
import com.example.mohamedrayen_fadhlaouilabexam.domain.model.Question

class QuizRepository {
    private val logo = R.drawable.ic_heritage_logo

    private val allQuestions = listOf(
        // --- ROMAN - EASY (5 Questions) ---
        Question(1, "Based on the image, identify this famous Roman amphitheater.", R.drawable.el_jem, listOf("Carthage", "El Jem", "Dougga", "Sbeitla"), 1, "The Amphitheatre of El Jem is one of the largest Roman amphitheaters in the world.", Category.ROMAN, Difficulty.EASY),
        Question(2, "In which ancient city can you find this majestic Capitol temple?", R.drawable.dougga, listOf("Dougga", "Sousse", "Tunis", "Kairouan"), 0, "The Capitol of Dougga is dedicated to Jupiter, Juno, and Minerva.", Category.ROMAN, Difficulty.EASY),
        Question(3, "Identify this city, famous for its unique underground villas.", R.drawable.bulla_regia, listOf("Bulla Regia", "Utica", "Uthina", "Maktar"), 0, "Bulla Regia has unique semi-subterranean housing to escape the heat.", Category.ROMAN, Difficulty.EASY),
        Question(4, "This image shows a rare triple temple layout. Name the city.", R.drawable.sbeitla, listOf("Sbeitla", "Uthina", "Gigthis", "Chemtou"), 0, "Sbeitla (Sufetula) features three distinct temples in its forum.", Category.ROMAN, Difficulty.EASY),
        Question(5, "Identify the city where these massive Antonine Bath ruins are located.", R.drawable.carthage, listOf("Sousse", "Tunis", "Carthage", "Bizerte"), 2, "The Baths of Antoninus in Carthage were the largest in Roman Africa.", Category.ROMAN, Difficulty.EASY),

        // --- ROMAN - MEDIUM (5 Questions) ---
        Question(31, "The yellow marble quarry shown in this image belongs to which city?", R.drawable.chemtou, listOf("Sbeitla", "Chemtou", "Haïdra", "Mustis"), 1, "Chemtou provided 'Giallo Antico' yellow marble to the Roman Empire.", Category.ROMAN, Difficulty.MEDIUM),
        Question(32, "This 'Water Temple' marks the start of a 130km aqueduct. Where is it?", R.drawable.zaghouan, listOf("Zaghouan", "Kairouan", "Carthage", "Dougga"), 0, "The Temple of Water in Zaghouan collected water for Carthage.", Category.ROMAN, Difficulty.MEDIUM),
        Question(33, "Identify this city, which served as the first capital of Roman Africa.", R.drawable.utica, listOf("Bizerte", "Sousse", "Utica", "Sfax"), 2, "Utica is one of the oldest settlements in Tunisia.", Category.ROMAN, Difficulty.MEDIUM),
        Question(34, "In which city is this well-preserved theater with a valley view located?", R.drawable.dougga, listOf("Sbeitla", "Uthina", "Dougga", "Chemtou"), 2, "The theater of Dougga is famous for its panoramic view.", Category.ROMAN, Difficulty.MEDIUM),
        Question(35, "This massive structure was the center of what Roman city?", R.drawable.el_jem, listOf("Thysdrus", "Hadrumetum", "Hippo Regius", "Thugga"), 0, "El Jem was known as Thysdrus, a wealthy olive oil hub.", Category.ROMAN, Difficulty.MEDIUM),

        // --- ROMAN - HARD (5 Questions) ---
        Question(61, "Identify this rare Libyco-Punic tower found in Dougga.", R.drawable.dougga, listOf("Mausoleum", "Theater", "Temple", "Arch"), 0, "The Libyco-Punic Mausoleum is a masterpiece of Numidian architecture.", Category.ROMAN, Difficulty.HARD),
        Question(62, "Which city is shown here, famous for its 'Schola Juventutis'?", R.drawable.ic_heritage_logo, listOf("Maktar", "Uthina", "Mustis", "Utica"), 0, "Maktar (Mactaris) features unique youth training facilities.", Category.ROMAN, Difficulty.HARD),
        Question(63, "This site was a major hub for Saharan trade. Identify it.", R.drawable.ic_heritage_logo, listOf("Gigthis", "Sbeitla", "Uthina", "Mustis"), 0, "Gigthis was a prosperous port city in the south.", Category.ROMAN, Difficulty.HARD),
        Question(64, "In which city can you find this massive Triumphal Arch of Diocletian?", R.drawable.ic_heritage_logo, listOf("Haïdra", "Thelepte", "Ammaedara", "Sbeitla"), 0, "Haïdra (Ammaedara) has one of the largest arches in the world.", Category.ROMAN, Difficulty.HARD),
        Question(65, "Identify the city home to this massive Roman amphitheater built into a hill.", R.drawable.ic_heritage_logo, listOf("Uthina", "Maktar", "Uthina", "Gigthis"), 0, "Uthina (Oudhna) features a large, partially restored amphitheater.", Category.ROMAN, Difficulty.HARD),

        // --- ISLAMIC - EASY (IDs 6-10) ---
        Question(6, "Identify this iconic Great Mosque, the first in North Africa.", R.drawable.kairouan, listOf("Zitouna", "Okba Mosque", "Bourguiba", "Testour"), 1, "The Great Mosque of Kairouan was founded in 670 AD.", Category.ISLAMIC, Difficulty.EASY),
        Question(7, "Which city's Medina is home to this ancient Zitouna Mosque?", R.drawable.zitouna, listOf("Sousse", "Kairouan", "Tunis", "Sfax"), 2, "The Al-Zaytuna Mosque is a symbol of the capital, Tunis.", Category.ISLAMIC, Difficulty.EASY),
        Question(8, "This coastal fortress is the oldest Ribat in the region. Name the city.", R.drawable.monastir, listOf("Sousse", "Monastir", "Mahdia", "Bizerte"), 1, "The Ribat of Monastir was a vital defensive structure.", Category.ISLAMIC, Difficulty.EASY),
        Question(9, "Identify this city, often called the fourth holiest in Islam.", R.drawable.kairouan, listOf("Tunis", "Sousse", "Kairouan", "Monastir"), 2, "Kairouan is a UNESCO World Heritage site and religious hub.", Category.ISLAMIC, Difficulty.EASY),
        Question(10, "This image shows a gate to a famous old Medina. Name the city.", R.drawable.medina_tunis, listOf("Sousse", "Tunis", "Sfax", "Mahdia"), 1, "Bab El Bhar is the gateway to the Medina of Tunis.", Category.ISLAMIC, Difficulty.EASY)
    ) + (11..90).filter { id -> 
        id !in listOf(1, 2, 3, 4, 5, 31, 32, 33, 34, 35, 61, 62, 63, 64, 65, 6, 7, 8, 9, 10) 
    }.map { id ->
        val category = when {
            id % 5 == 1 -> Category.PUNIC
            id % 5 == 2 -> Category.MODERN
            id % 5 == 3 -> Category.NATURAL
            id % 5 == 4 -> Category.CITIES
            else -> Category.ISLAMIC
        }
        val difficulty = when {
            id < 31 -> Difficulty.EASY
            id < 61 -> Difficulty.MEDIUM
            else -> Difficulty.HARD
        }
        Question(id, "Based on the image, identify this ${category.name} site.", logo, listOf("Option A", "Option B", "Correct Answer", "Option D"), 2, "Explore Tunisia's 3,000 years of history!", category, difficulty)
    }

    fun getQuestions(category: Category?, difficulty: Difficulty): List<Question> {
        return allQuestions.filter { 
            (category == null || it.category == category) && it.difficulty == difficulty 
        }.shuffled().take(5)
    }
}
