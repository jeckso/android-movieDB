package com.jeckso.moviedb.domain.repository

sealed class Specification

abstract class RetrieveSpec : Specification()

abstract class UpdateSpec : Specification()

abstract class DeleteSpec : Specification()