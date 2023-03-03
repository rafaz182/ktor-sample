package dev.rafaz.database

import dev.rafaz.database.daoimpl.ArticleDAOImpl
import dev.rafaz.database.daoimpl.CategoryDAOImpl
import dev.rafaz.database.daoimpl.GeneralAttributesDAOImpl
import dev.rafaz.database.daoimpl.GeneralOptionDAOImpl

object DAO {
    val Article: ArticleDAO                     by lazy { ArticleDAOImpl() }
    val Category: CategoryDAO                by lazy { CategoryDAOImpl() }
    val GeneralOptions: GeneralOptionsDAO       by lazy { GeneralOptionDAOImpl() }
    val GeneralAttributes: GeneralAttributesDAO by lazy { GeneralAttributesDAOImpl(GeneralOptions) }
}