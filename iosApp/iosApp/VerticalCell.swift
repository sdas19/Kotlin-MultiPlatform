//
//  VerticalCell.swift
//  iosApp
//
//  Created by Soumyajit Das on 14/12/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import UIKit
import shared

class VerticalCell: UITableViewCell {

    @IBOutlet weak var title: UITextView!
    
    @IBOutlet weak var ingredient: UITextView!
    
    
    func setData(recipe: BrewaryResponseItem) {
        title.text = recipe.name
        ingredient.text = recipe.description_
    }
}
